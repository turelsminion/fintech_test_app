package com.fintech.testapp.activities;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.data.local.model.BankAccountModel;

import java.util.List;

public class BankAccountDiffCallBack extends DiffUtil.Callback {

	private final List<BankAccountDetailsModel> oldList;
	private final List<BankAccountDetailsModel> newList;

	public BankAccountDiffCallBack(List<BankAccountDetailsModel> oldList, List<BankAccountDetailsModel> newList) {
		this.oldList = oldList;
		this.newList = newList;
	}

	@Override
	public int getOldListSize() {
		return oldList == null ? 0 : oldList.size();
	}

	@Override
	public int getNewListSize() {
		return newList == null ? 0 : newList.size();
	}

	@Override
	public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
		BankAccountDetailsModel oldItem = oldList.get(oldItemPosition);
		BankAccountDetailsModel newItem = newList.get(newItemPosition);

		return oldItem.getId() == newItem.getId();
	}

	@Override
	public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
		final BankAccountDetailsModel oldItem = oldList.get(oldItemPosition);
		final BankAccountDetailsModel newItem = newList.get(newItemPosition);

		return oldItem.equals(newItem);
	}

	@Nullable
	@Override
	public Object getChangePayload(int oldItemPosition, int newItemPosition) {
		// Implement method if you're going to use ItemAnimator
		return super.getChangePayload(oldItemPosition, newItemPosition);
	}
}
