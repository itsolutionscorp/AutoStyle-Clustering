class Array
  def accumulate
    return self unless block_given?

    [].tap { |result| each { |element| result << yield(element) } }
  end
end
