class Scale
	@@TONES = ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#']
		.zip(['A', 'Bb', 'B', 'C', 'Db', 'D', 'Eb', 'E', 'F', 'Gb', 'G', 'Ab'])
	@@STEPS = 'UmMA'.chars
	
	def initialize(tonic, type, intervals='m'*12)
		@tonic = tonic
		@type = type
		@intervals = intervals.chars
	end
	
	def name()
		"#{@tonic.capitalize} #{@type.to_s}"
	end
	
	def pitches()
		index = @@TONES.index{|tonic| tonic.include?(@tonic.capitalize)}
		@intervals.each_with_object([]) do |c, result| 
			result << @@TONES[index][(flat?) ? 1 : 0]
			index = (index + @@STEPS.index(c)) % @@TONES.length
		end
	end
	
	private 
	
	def flat?()
		(@tonic.length == 1 && 'Fcdfg'.chars.include?(@tonic)) || (@tonic.length == 2 && @tonic.chars.last == 'b')
	end
end
