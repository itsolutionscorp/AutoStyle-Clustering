class Integer

  def to_roman
    arr = self.to_s.scan(/./).map {|e| e.to_i }
    ones = arr.last
    countone = 0
    arr1 = []
    arr2 = []
    arr3 = []
    arr4 = []
    (0...ones).each do |n|
      countone += 1
      arr1 << "I"
      arr1.pop(3) && arr1 << "V" if countone == 4
      arr1.shift && arr1.pop  if countone == 5
      arr1.shift(4) && arr1 << "X" if countone == 9
    end
    if arr[-2]
      tens = arr[-2]
      countten = 0
      (0...tens).each do |n|
        countten += 1
        arr2 << "X"
        arr2.pop(3) && arr2 << "L" if countten == 4
        arr2.shift && arr2.pop if countten == 5
        arr2.shift(4) && arr2 << "C" if countten == 9
      end
    end
    if arr[-3]
      huns = arr[-3]
      counthun = 0
      (0...huns).each do |n|
        counthun += 1
        arr3 << "C"
        arr3.pop(3) && arr3 << "D" if counthun == 4
        arr3.shift && arr3.pop if counthun == 5
        arr3.shift(4) && arr3 << "M" if counthun == 9
      end
    end
    if arr[-4]
      thous = arr[-4]
      countthou = 0
      (0...thous).each do |n|
        countthou += 1
        arr4 << "M"
      end
    end
    (arr4 + arr3 + arr2 + arr1).join
end

end
