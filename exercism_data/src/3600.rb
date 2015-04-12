class Hamming

  def compute(string1, string2)
    #identical case
    return 0 if string1 == string2

    mutations = 0
    string1.split('').each_with_index  do |char,index|
      if char != string2[index]
        mutations += 1
      end
    end

    mutations
  end
end
