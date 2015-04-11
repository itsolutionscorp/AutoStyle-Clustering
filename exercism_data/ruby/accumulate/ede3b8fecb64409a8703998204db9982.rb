class Array
  def accumulate(&block)
    [].tap do |out|
      self.each { |e| out << yield(e) }
    end
  end

end
