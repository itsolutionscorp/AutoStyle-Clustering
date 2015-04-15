require 'thread'

class Prime
	class << self
		def nth(index)
			raise ArgumentError if index == 0 
			seive_batch_size = 4 * index
			primes = Seive.new(seive_batch_size).primes
			seive_end = 2 * seive_batch_size
			seive_start = seive_batch_size + 1
			while primes.length < index
				primes = Seive.new(seive_end, seive_start, primes).primes
				seive_start = seive_end + 1
				seive_end += seive_batch_size
			end
			primes[index - 1]
		end
	end
end

#Adapted from http://stackoverflow.com/a/17188457
class Pool
  def initialize(size)
    @size = size
    @jobs = Queue.new
    @pool = Array.new(@size) {|pool_id| create_thread(pool_id)}
  end

  def create_thread(pool_id)
	  Thread.new do
		Thread.current[:id] = pool_id
		catch(:exit) do
		  loop do
			job, args = @jobs.pop
			Thread.pass if job == nil
			job.call(*args)
		  end
		end
	  end
  end

  def schedule(*args, &block)
	  @jobs << [block, args]
  end

  def wait_for_termination
	@pool.map(&:join)
  end

  def shutdown
    @size.times{ schedule { throw :exit } }
    wait_for_termination
  end
end



class Seive
	attr_reader :primes

	def initialize(end_index, start_index = 3, primes = [2])
		@end_index = end_index
		@mutex = Mutex.new
		@start_index = make_odd_if_not(start_index)
		@primes = primes
		@tpool = Pool.new(100)
		run
	end

	private

	def make_odd_if_not(start_index)
		(start_index % 2 == 0) ? start_index + 1 : start_index
	end

	def run
		all_candidate_primes = (@start_index..@end_index).step(2).to_a
		candidate_primes = filter_known_composites(all_candidate_primes)
		@primes += candidate_primes
		candidate_primes.each_with_index do |candidate, index|
			schedule_cleansing_for(candidate, candidate_primes.drop(index + 1))
		end
		sleep(1)
		@tpool.shutdown
	end

	def filter_known_composites(all_candidates)
		all_candidates.select { |candidate| is_prime_to_known_factors?(candidate) }
	end

	def is_prime_to_known_factors?(candidate)
		@primes.find{|p| candidate % p == 0 } == nil
	end

	def schedule_cleansing_for(candidate, candidate_primes)
		size = candidate_primes.length
		@tpool.schedule(candidate) do
			cleanse_primes_using(candidate, candidate_primes)
		end
	end

	def cleanse_primes_using(filtering_candidate, candidates_list)
		non_primes = candidates_list.select{|cp| (cp % filtering_candidate == 0)}
		non_primes.each {|non_prime| remove_from_primes(non_prime)}
	end

	def remove_from_primes(non_prime)
		@mutex.synchronize do
			@primes.delete(non_prime)
		end
	end
end
