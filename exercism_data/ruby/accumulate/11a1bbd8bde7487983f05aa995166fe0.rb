class Array
  def accumulate
    answer = []
    each { |n| answer << (yield n) }
    answer
  end
end
