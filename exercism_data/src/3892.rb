def compute(str1, str2)
  	mutations = 0

  	str1.split("").each_with_index do |base, index|
      if base != str2[index] && !str2[index].nil?
        mutations += 1
  	  end
  	end
  	mutations
  end