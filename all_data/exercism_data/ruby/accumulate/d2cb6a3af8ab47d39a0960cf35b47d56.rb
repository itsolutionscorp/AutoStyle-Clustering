class Array
  def accumulate(&block)
    return enum_for(:accumulate) unless block_given?
    a = []

    for i in 0...length
      a[i] = yield self[i]
    end

    a
  end
end
