class Array

  def accumulate
    inject([]) do |acc, item|
      acc << yield(item)
    end
  end

end
