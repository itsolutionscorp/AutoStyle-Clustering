class Array
  def accumulate
    results = []
    each {|b| results << yield(b) }
    results
  end
end
