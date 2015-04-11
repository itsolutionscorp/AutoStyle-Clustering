class Array
  def accumulate
    return enum_for(:accumulate) unless block_given?

    each_with_object([]) { |e, arr| arr << yield(e) }
  end
end
