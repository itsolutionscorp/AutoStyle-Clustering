class Array
  def accumulate
    new_array = []
    self.each { |e| new_array << yield(e) }
    new_array
  end
end
