Array.class_eval do
  def keep &block
    self.- discard(&block)
  end

  def discard &block
    dup.delete_if(&block)
  end
end
