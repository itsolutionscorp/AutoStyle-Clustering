class Hamming
  # First take, lack of count_with_index and the comparison having to be last
  # makes for some ugly i = -1.  Could put b[i+=1] but not as readable.
  # def self.compute a, b
  #   i = -1
  #   a.chars.count do |c|
  #     i += 1
  #     c != b[i]
  #   end
  # end

  # Felt this was more readable 
  def self.compute a, b
    result = 0
    a.chars.each_with_index do |c, i|
      result += 1 if c != b[i]
    end
    result
  end
end
