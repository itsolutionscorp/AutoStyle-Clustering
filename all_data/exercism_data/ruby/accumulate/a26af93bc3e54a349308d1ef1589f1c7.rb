class Array

  def accumulate
    result = []
    each_with_object([]) do |element, acc|
      acc << yield(element)
    end
  end
  
end
