class Array
  def keep
    kept = []
    each do |item|
      kept << item if yield item
    end
    kept
  end

  def discard
    keep { |item| not yield item }
  end
end
