class Array
  def accumulate
    [].tap{|result| each{|element| result << yield(element) } }
  end
end
