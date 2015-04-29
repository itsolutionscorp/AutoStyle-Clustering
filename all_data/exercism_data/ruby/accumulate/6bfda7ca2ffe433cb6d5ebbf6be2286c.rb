class Array
  def accumulate
    [].tap do |result|
      (0...size).each do |i|
        result << (yield at(i))
      end
    end
  end
end
