class Array
  def keep &condition
    self.select(&condition)
  end

  def discard &condition
    self.delete_if(&condition)
  end
end
