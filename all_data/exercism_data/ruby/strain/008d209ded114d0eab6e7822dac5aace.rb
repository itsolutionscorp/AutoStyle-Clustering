class Array
  # credit to http://exercism.io/submissions/9793e12b1db6a2929df004d1 and http://exercism.io/submissions/013ba6285d971b109745eced

  def keep(&block)
    keep_if { |i| block.call(i) }
  end

  def discard(&block)
    keep_if { |i| !block.call(i) }
  end
end
