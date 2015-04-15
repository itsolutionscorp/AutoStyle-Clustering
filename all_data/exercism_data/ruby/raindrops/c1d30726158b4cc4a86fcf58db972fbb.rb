class Raindrops
  class << self
	def convert n
		rsp = ''
		rsp = 'Pling' if n % 3 == 0
		rsp += 'Plang' if n % 5 == 0
		rsp += 'Plong' if n % 7 == 0
			 
		rsp=n.to_s if rsp==''
		rsp
	end
  end
end
