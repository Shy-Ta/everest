package com.everest.samples.core;

import java.util.ArrayList;
import java.util.List;

//Power set of a set - 2^n combinations possible
//O(n) memory
public class SubsetsOfSet {

	public static void main(String[] args) {

		SubsetsOfSet soa = new SubsetsOfSet();
		int[] nums = { 1, 2, 3 };
		soa.subsets(nums);
//		List<List<Integer>> subsets = soa.subsets(nums);
//
//		for (List<Integer> subset : subsets) {
//			System.out.println(subset);
//		}
	}

//	public List<List<Integer>> subsets(int[] nums) {
	void subsets(int[] nums) {
//		List<List<Integer>> list = new ArrayList<>();
//		subsetsHelper(list, new ArrayList<>(), nums, 0);
		subsetsHelper(new ArrayList<>(), nums, 0);
//		return list;
	}

//	private void subsetsHelper(List<List<Integer>> list, List<Integer> resultList, int[] nums, int start) {
	private void subsetsHelper(List<Integer> resultList, int[] nums, int start) {
		List<Integer> result = new ArrayList<>(resultList);
		System.out.println(result);
//		list.add(result);
		for (int i = start; i < nums.length; i++) {
			// add element
			resultList.add(nums[i]);
			// Explore
//			subsetsHelper(list, resultList, nums, i + 1);
			subsetsHelper(resultList, nums, i + 1);
			// remove
			resultList.remove(resultList.size() - 1);
//			Integer removedElement = resultList.remove(resultList.size() - 1);
//			System.out.println("Removed:" + removedElement);
		}
	}

}
