
class CircularBuffer

  class BufferEmptyException < TypeError
  end

  class BufferFullException < TypeError
  end

  def initialize(size)
    @size = size
    @front = 0
    @end = 0
    @buffer = Array.new(size)
    @count = 0
  end

  def write(data)
    if @size == @count then raise BufferFullException end 

    data.nil? ? return :  @buffer[@front] = data
    @front == @size - 1 ?  @front = 0 :  @front += 1    
    @count += 1
  end

  def write!(data)
    if @size == @count then read end #overwrite oldest valid entry
    write(data)
  end
  
  def read
    if @count == 0 then raise BufferEmptyException end
    out = @buffer[@end]
    @end == @size - 1 ?  @end = 0 : @end += 1      
    @count -= 1   
    out  
  end

  def clear
    @front = 0
    @end = 0
    @count = 0
  end

end  

