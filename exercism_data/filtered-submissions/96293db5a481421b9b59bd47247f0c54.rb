class Hamming
  class << self
    def compute(first, second)
      count       = 0
      first_enum  = first.each_char
      second_enum = second.each_char

      begin
        while true
          count += 1 unless first_enum.next == second_enum.next
        end
      rescue StopIteration
      end

      count
    end
  end
end
