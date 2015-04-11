class Array
  def accumulate
    [].tap { |array| each { |object| array << yield(object) } }
  end
end
