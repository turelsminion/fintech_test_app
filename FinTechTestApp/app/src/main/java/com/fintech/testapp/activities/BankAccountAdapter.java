package com.fintech.testapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.testapp.R;
import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.data.local.model.BankAccountModel;
import com.fintech.testapp.util.Utils;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAccountAdapter extends RecyclerView.Adapter<BankAccountAdapter.StatementViewHolder> {

	private static final String TAG = "BankAccountAdapter";

	private Context context;
	private List<BankAccountDetailsModel> list;
	private String accountNumber;

	public BankAccountAdapter(Context context, List<BankAccountDetailsModel> list, String accountNumber) {
		this.context = context;
		this.list = list;
		this.accountNumber = accountNumber;
	}

	@NonNull
	@Override
	public StatementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statement, parent, false);

		return new BankAccountAdapter.StatementViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull StatementViewHolder holder, int position) {
		holder.bindView(position);
	}

	@Override
	public int getItemCount() {
		return this.list != null ? this.list.size() : 0;
	}

	public void updateBankAccountDetailsListItems(List<BankAccountDetailsModel> models) {
		final BankAccountDiffCallBack diffCallback = new BankAccountDiffCallBack(this.list, models);
		final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

		this.list.clear();
		this.list.addAll(models);
		diffResult.dispatchUpdatesTo(this);
	}

	class StatementViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.statementTypeImage) ImageView statementTypeImage;
		@BindView(R.id.statementText) TextView statementText;
		@BindView(R.id.statementDate) TextView statementDate;
		@BindView(R.id.statementCount) TextView statementCount;

		public StatementViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		private void bindView(int position) {
			BankAccountDetailsModel details = list.get(position);

			switch (details.getTransactionType()) {
				case P2P:
					if (details.getTransactionCount() > 0) {
						statementTypeImage.setImageResource(R.drawable.shape_p2p_supply);
						statementCount.setTextColor(context.getResources().getColor(R.color.colorGreen));
						statementCount.setText(context.getString(R.string.supply_template, details.getTransactionCount()));
						statementText.setTextColor(context.getResources().getColor(R.color.colorGreen));
						statementText.setText(context.getString(R.string.p2p_template, "from", details.getBeneficiary()));
					} else {
						statementTypeImage.setImageResource(R.drawable.shape_p2p_pay);
						statementCount.setTextColor(context.getResources().getColor(R.color.colorRed));
						statementCount.setText(context.getString(R.string.pay_template, details.getTransactionCount()));
						statementText.setTextColor(context.getResources().getColor(R.color.colorGreen));
						statementText.setText(context.getString(R.string.p2p_template, "to", details.getBeneficiary()));
					}
					break;
				case SUPPLY:
					statementTypeImage.setImageResource(R.drawable.shape_supply);
					statementCount.setTextColor(context.getResources().getColor(R.color.colorGreen));
					statementCount.setText(context.getString(R.string.supply_template, details.getTransactionCount()));
					statementText.setTextColor(context.getResources().getColor(R.color.colorGreen));
					statementText.setText(context.getString(R.string.supply_text_template, accountNumber.substring(accountNumber.length() - 4)));
					break;
				case PAYMENT:
					statementTypeImage.setImageResource(R.drawable.shape_payment);
					statementCount.setTextColor(context.getResources().getColor(R.color.colorRed));
					statementCount.setText(context.getString(R.string.pay_template, details.getTransactionCount()));
					statementText.setTextColor(context.getResources().getColor(R.color.colorRed));
					statementText.setText(details.getBeneficiary());
					break;
				case WITHDRAW:
					statementTypeImage.setImageResource(R.drawable.shape_payment);
					statementCount.setTextColor(context.getResources().getColor(R.color.colorRed));
					statementCount.setText(context.getString(R.string.pay_template, details.getTransactionCount()));
					statementText.setTextColor(context.getResources().getColor(R.color.colorRed));
					statementText.setText(context.getString(R.string.cash_withdraw_template, accountNumber.substring(accountNumber.length() - 4)));
			}
			statementDate.setText(Utils.formatDate(details.getTransactionDate()));
		}
	}

}
