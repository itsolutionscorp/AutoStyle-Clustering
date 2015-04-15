def compute(str1, str2)
  	mutations = 0

  	str1.split("").each_with_index do |base, index|
      if !str2[index].nil? && base != str2[index]
        mutations += 1
  	  end
  	end
  	mutations
  end