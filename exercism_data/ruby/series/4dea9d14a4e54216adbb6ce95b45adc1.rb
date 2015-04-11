Series = Struct.new(:s) do
  def slices(n)
    raise ArgumentError if n > self.s.length
    self.s.split(//).map(&:to_i).each_cons(n).to_a
  end
end
