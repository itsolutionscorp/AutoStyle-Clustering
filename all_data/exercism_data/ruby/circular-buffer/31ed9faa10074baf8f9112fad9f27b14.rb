require 'pry'

class CircularBuffer
  def initialize(slots)
    @storage = Array.new(slots)
    @position = 0
    @oldest_element = 0
  end

  def read
    read_data { raise BufferEmptyException if buffer_empty? }
  end
  
  def write(data)
    write_data(data) { raise BufferFullException if storage_unavailable? }
  end

  def write!(data)
    write_data(data)
    bump_read_position
  end

  def clear
    @storage = Array.new(@storage.size)
  end
  
  private

  def read_data
    yield if block_given?
    slot_data = @storage[@position]
    @storage[@position] = nil
    bump_read_position
    slot_data
  end

  def write_data(data)
    return if data == nil
    yield if block_given?
    @storage[@oldest_element] = data
    bump_oldest_position
  end
  
  def bump_oldest_position
    @oldest_element == @storage.size - 1 ? @oldest_element = 0 : @oldest_element += 1
  end

  def bump_read_position
    @position == @storage.size - 1 ? @position = 0 : @position += 1
  end

  def storage_unavailable?
    @storage.none? { |e| e.nil? }
  end

  def buffer_empty?
    @storage.all? { |e| e.nil? }
  end

  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end
end
