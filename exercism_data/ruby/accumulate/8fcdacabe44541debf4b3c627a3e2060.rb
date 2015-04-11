class Array
  def accumulate
    self.map { |item| proc.call(item) }
  end
end
