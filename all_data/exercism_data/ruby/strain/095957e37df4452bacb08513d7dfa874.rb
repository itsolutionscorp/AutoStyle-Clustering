# overwriting class Array
class Array

  def keep
    op = []
    each { |e| op << e if yield(e) }
    op
  end

  def discard
    op = clone
    each do |e|
      op.delete(e) if yield(e)
    end
    op
  end

end
