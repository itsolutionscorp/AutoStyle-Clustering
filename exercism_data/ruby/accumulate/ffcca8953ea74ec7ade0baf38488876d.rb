module  Accumulate
  def accumulate( &block )
    self.each_with_object([]) {|i, out| x =  block.call(i) ; out << x}
  end
end

Array.send :include, Accumulate
