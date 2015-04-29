class Array
  def accumulate &operation
    map { |object| operation.call(object) }
  end
end
