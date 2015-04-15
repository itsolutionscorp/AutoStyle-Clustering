class Array
  def accumulate &block
    result = []

    each { |element| result << (yield element) }

    result
  end
end
