def compute(str1, str2)
    count = 0
	return 0 if str1 == str2
	for i in (0...str1.length)
	  count += 1 if str1[i] != str2[i]
	end
	count
  end