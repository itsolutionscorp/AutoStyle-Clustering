class Deque
  Item = Struct.new(:value, :prev, :next)
  def initialize
    @start = Item.new
    @start.prev = @start
    @start.next = @start
  end

  def push(value)
    list_item = Item.new(value)
    list_item.next = @start
    if @start.next.value.nil?
      list_item.prev = @start
      @start.next = list_item
      @start.prev = list_item
    else
      list_item.prev = @start.prev
      @start.prev.next = list_item
      @start.prev = list_item
    end
  end

  def pop(num_of_items = 1)
    popped_value = @start.prev[:value]
    num_of_items.times do
      @start.prev.prev.next = @start
      @start.prev = @start.prev.prev

    end
    return popped_value
  end

  def shift(num_of_items = 1)
    shifted_value = @start.next[:value]
    num_of_items.times do
      @start.next.next.prev = @start
      @start.next = @start.next.next

    end
    return shifted_value
  end

  def unshift(value)
    list_item = Item.new(value)
    list_item.prev = @start
    if @start.prev.value.nil?
      list_item.next = @start
      @start.next = list_item
      @start.prev = list_item
    else
      list_item.next = @start.next
      @start.next.prev = list_item
      @start.next = list_item
    end
  end
end
