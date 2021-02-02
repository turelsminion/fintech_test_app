package com.fintech.testapp.activities;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.fintech.testapp.data.local.model.BankAccountModel;

import java.util.List;

public class BankAccountsDiffCallBack extends DiffUtil.Callback {

	private final List<BankAccountModel> oldList;
	private final List<BankAccountModel> newList;

	public BankAccountsDiffCallBack(List<BankAccountModel> oldList, List<BankAccountModel> newList) {
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
		BankAccountModel oldItem = oldList.get(oldItemPosition);
		BankAccountModel newItem = newList.get(newItemPosition);

		return oldItem.getId() == newItem.getId();
	}

	@Override
	public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
		final BankAccountModel oldItem = oldList.get(oldItemPosition);
		final BankAccountModel newItem = newList.get(newItemPosition);

		return oldItem.equals(newItem);
	}

	@Nullable
	@Override
	public Object getChangePayload(int oldItemPosition, int newItemPosition) {
		// Implement method if you're going to use ItemAnimator
		return super.getChangePayload(oldItemPosition, newItemPosition);
	}
}
