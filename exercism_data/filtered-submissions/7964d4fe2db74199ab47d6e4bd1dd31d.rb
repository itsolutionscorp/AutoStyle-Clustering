def compute str1, str2
    diff = []
    arr1, arr2 = str1.split(""), str2.split("")

    arr1.each_with_index do |item, index|
      diff << item if item != arr2[index] and index < arr2.size
    end

    diff.size
  end