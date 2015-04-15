class Array
  def accumulate
    new = []
    each do |item|
      new << yield(item)
    end
    new
  end
end
