class Array
  def keep(&block)
    acc = []
    each { |a| acc.push(a) if block.call(a) }
    acc
  end

  def discard(&block)
    acc = []
    each { |a| acc.push(a) unless block.call(a) }
    acc
  end
end
