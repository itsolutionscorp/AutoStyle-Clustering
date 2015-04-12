class Hamming

  def compute string1, string2
    count = 0
    string1.each_char.with_index(0) do |char, i|
      return count if (string2[i].nil? || char.nil?)
      if char != string2[i]
        count += 1
      end
    end
    count
  end

end
