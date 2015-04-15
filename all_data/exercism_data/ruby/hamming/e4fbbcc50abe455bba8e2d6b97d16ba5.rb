class Hamming
  def self.compute(string1, string2)
    first_string = string1.chars
    second_string = string2.chars
    count = 0

    for i in first_string
      if first_string[i] == second_string[i]
        count += 1
      end
    end
    return count
  end
end    




    # class Hamming
    #   def self.compute(string1, string2)
    #     count = 0

    #     string1.each_char do |c|
    #       string2.each_char do |d|
    #         if c != d
    #           count += 1;
    #         end #ends if

    #       end #ends string2 block
    #     end #ends string1 block
    #     return count
    #   end #end compute method
    # end #ends Hamming class
