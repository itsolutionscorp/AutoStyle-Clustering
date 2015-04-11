class Array
  def accumulate(&block)
    map do |i|
      i.instance_eval(&block)
    end
  end
end
