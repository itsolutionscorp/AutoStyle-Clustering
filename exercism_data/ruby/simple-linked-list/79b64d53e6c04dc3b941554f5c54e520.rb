# class LinkedList

  class Element

    attr_accessor :datum, :next

    def initialize(datum, follower)
      
      @datum = datum
    end
  end

  def to_s
    @datum
  end

# end

# node1 = Element.new("cats")

# p node1
