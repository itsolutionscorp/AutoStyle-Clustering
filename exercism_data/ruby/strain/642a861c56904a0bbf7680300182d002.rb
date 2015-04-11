class Array

  def keep(&block)
    inject([]) do |values_to_keep, current|
      block[current] ? values_to_keep + [current] : values_to_keep
    end
  end

  def discard(&block)
    keep(&block.complement)
  end
end

class Proc
  def complement
    proc {|*args| !call(*args) }
  end
end
