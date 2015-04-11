class Array
  def keep
    self.delete_if { |x| not yield x }
  end

  def discard
    self.delete_if { |x| yield x }
  end
end
