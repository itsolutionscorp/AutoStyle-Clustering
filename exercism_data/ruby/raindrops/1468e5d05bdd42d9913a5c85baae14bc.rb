module Raindrops
	NUM_MAP = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	def self.convert(num)
		content = ''
		NUM_MAP.each do |key, value|
			content += value if num % key == 0
		end
		content.empty? ? num.to_s : content
	end
end
