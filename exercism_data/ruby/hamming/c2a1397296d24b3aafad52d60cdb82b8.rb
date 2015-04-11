module Hamming
  def self.compute(string1, string2)
    enum1 = string1.each_char
    enum2 = string2.each_char
    count = 0
    begin
      loop do
        count +=1 if enum1.next != enum2.next
      end
    rescue StopIteration
    end
    return count
  end
end
