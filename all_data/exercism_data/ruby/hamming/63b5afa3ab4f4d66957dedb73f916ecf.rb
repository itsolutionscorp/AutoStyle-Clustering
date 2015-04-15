class Hamming
  def self.compute(first,second)
    length = [first.length, second.length].min
    firstArr = first[0,length].split ""
    secondArr = second[0,length].split ""
    firstArr.zip(secondArr).reduce 0 do |sum,row| 
      equal_char = row[0] == row[1]
      sum + (equal_char ? 0 : 1)
    end        
  end
end
