class Array

  def accumulate
    result = []

    each do |element_in_array|
      result << yield(element_in_array)
    end

    result
  end
end
