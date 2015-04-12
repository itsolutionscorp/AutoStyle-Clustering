class Hamming
  def compute(first,second)
    firstArr = first.split ""
    secondArr = second.split ""
    firstArr.zip(secondArr).reduce 0 do |sum,row| 
      equal_char = row[0] == row[1] || row[0].nil? || row[1].nil?
      sum + (equal_char ? 0 : 1)
    end        
  end
end
