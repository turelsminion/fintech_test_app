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
import com.fintech.testapp.data.local.model.BankAccountModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAccountsAdapter extends RecyclerView.Adapter<BankAccountsAdapter.ViewHolder> {

	private final static String TAG = "BankAccountsAdapter";

	private Context context;
	private List<BankAccountModel> list;
	private View.OnClickListener onItemClickListener;

	public BankAccountsAdapter(Context context, List<BankAccountModel> list) {
		this.context = context;
		this.list = list;
	}

	public void setItemClickListener(View.OnClickListener clickListener) {
		onItemClickListener = clickListener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_account, parent, false);

		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.bindView(position);
	}

	@Override
	public int getItemCount() {
		return list != null ? list.size() : 0;
	}

	public void updateBankAccountsListItems(List<BankAccountModel> models) {
		final BankAccountsDiffCallBack diffCallback = new BankAccountsDiffCallBack(this.list, models);
		final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

		this.list.clear();
		this.list.addAll(models);
		diffResult.dispatchUpdatesTo(this);
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.cardTypeImage) ImageView cardTypeImage;
		@BindView(R.id.cardTypeName) TextView cardType;
		@BindView(R.id.cardNumber) TextView cardNumber;
		@BindView(R.id.balance) TextView balance;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setTag(this);
			itemView.setOnClickListener(onItemClickListener);
			ButterKnife.bind(this, itemView);
		}

		private void bindView(int position) {
			BankAccountModel model = list.get(position);

			switch (model.getCardType()) {
				case VISA:
					cardTypeImage.setImageResource(R.drawable.ic_visa);
					cardType.setText(R.string.visa);
					break;
				case MASTER_CARD:
					cardTypeImage.setImageResource(R.drawable.ic_mastercard);
					cardType.setText(R.string.master_card);
					break;
				case MAESTRO:
					cardTypeImage.setImageResource(R.drawable.ic_maestro);
					cardType.setText(R.string.maestro);
					break;
			}

			cardNumber.setText(context.getString(R.string.card_number, model.getCardNumber().substring(model.getCardNumber().length() - 4)));
			balance.setText(context.getString(R.string.balance, model.getBalance(), model.getCurrencyType().toString()));

		}
	}
}
