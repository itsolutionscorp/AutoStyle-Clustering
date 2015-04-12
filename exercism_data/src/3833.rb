def compute(list1, list2)
        list1 = list1.split(//)
        list2 = list2.split(//)
        list1.zip(list2).map{
            |a, b| a == b ? 0 : 1
        }.inject(:+)
    end