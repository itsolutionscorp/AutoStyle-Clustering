module Hamming
  def self.compute input1, input2
    short,long = input1,input2 unless input1.length > input2.length
    short,long = input2,input1 if short.nil?

    short.split('').each_with_index.map{|x, i| short[i]==long[i]}.reject{|j| j==true}.count
  end
end
