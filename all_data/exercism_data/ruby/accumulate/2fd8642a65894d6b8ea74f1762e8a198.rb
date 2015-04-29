class Array
  def accumulate
    inject([]) {|memo, e| memo << yield(e)}
  end
end
