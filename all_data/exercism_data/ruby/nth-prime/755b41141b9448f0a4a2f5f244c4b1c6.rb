require 'thread'

class Prime
	class << self
		def nth(index)
			raise ArgumentError if index == 0 
			base_size = 4 * index
			primes = Seive.new(base_size).primes
			seive_end = 2 * base_size
			seive_start = base_size + 1
			while primes.length < index
				primes = Seive.new(seive_end, start = seive_start, primes).primes
				seive_start = seive_end + 1
				seive_end += base_size
			end
			primes[index - 1]
		end
	end
end

class Pool
  def initialize(size)
    @size = size
    @jobs = Queue.new
    @pool = Array.new(@size) do |i|
      Thread.new do
        Thread.current[:id] = i
        catch(:exit) do
          loop do
            job, args = @jobs.pop
            job.call(*args)
          end
        end
      end
    end
  end

  def schedule(*args, &block)
    @jobs << [block, args]
  end

  def shutdown
    @size.times do
      schedule { throw :exit }
    end
    @pool.map(&:join)
  end
end



class Seive
	attr_reader :primes

	def initialize(end_index, start = 3, primes = [2])
		@end_index = end_index
		@mutex = Mutex.new
		@start = (start % 2 == 0) ? start + 1 : start
		@primes = primes
		@tpool = Pool.new(100)
		calculate_primes
	end

	def calculate_primes
		@all_candidate_primes = (@start..@end_index).step(2).to_a
		@candidate_primes = @all_candidate_primes.select do |candidate_prime|
			@primes.find{|p| candidate_prime % p == 0 } == nil
		end
		@primes += @candidate_primes
		@candidate_primes.each do |candidate|
			make_chunked_threads_for(candidate)
		end
		sleep(1)
	end

	def make_chunked_threads_for(candidate)
		size = @candidate_primes.length
		@candidate_primes.each_slice((size/2.0).ceil) do |primes_slice|
			 @tpool.schedule { cleanse_candidate_primes_list(candidate, primes_slice) }
		end
	end

	def cleanse_candidate_primes_list(candidate, numbers_list)
		non_primes = numbers_list.select{|cp| (cp>candidate) && (cp % candidate == 0)}
		non_primes.each do |non_prime|
			@mutex.synchronize do
				@primes.delete(non_prime)
			end
		end
	end
end
