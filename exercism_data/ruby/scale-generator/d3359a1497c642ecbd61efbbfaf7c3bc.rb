class Scale
	attr_reader :key, :scale

	MINOR_SCALES = [:minor, :harmonic_minor, :locrian]

	def initialize(key, scale, interval_pattern = "m"*12)
		@scale = scale.to_sym
		@key = Key.new(key, type)
		
		@intervals = Interval.create(interval_pattern)
	end

	def name
		key.name + " " + scale.to_s
	end

	def pitches
		[].tap do |notes|
			@intervals.each.reduce(@key.pitch) do |pitch, interval|
				notes << pitch.note(@key.key_type)
				pitch.next(interval)
			end
		end
	end

	def type
		MINOR_SCALES.include?(scale) ? :minor : :major
	end
	
end

class Interval
	attr_reader :value, :name

	INTERVALS = {
		"M" => 2,
		"m" => 1,
		"A" => 3
	}

	def initialize(name)
		@name = name
		@value = INTERVALS[name] || 0
	end

	def self.create(pattern)
		pattern.chars.map do |interval|
			Interval.new(interval)
		end
	end

end

class Key
	attr_reader :type, :pitch

	KEYS = {
		major: {
			"A" => :sharp, "B" => :sharp, "D" => :sharp, 
			"E" => :sharp, "F" => :flat,  "G" => :sharp
		},
		minor: {
			"B" => :sharp, "C" => :flat, "D" => :flat,
			"E" => :sharp, "F" => :flat, "G" => :flat
		}
	}
	
	def initialize(note, scale_type)
		@scale_type = scale_type
		@pitch = Pitch.new(note: note)
	end

	def name
		@pitch.note
	end

	def key_type
		@key_type ||= 
			case
			when @pitch.sharp? then :sharp
			when @pitch.flat?  then :flat
			else 
				KEYS[@scale_type][name] || :sharp
			end
	end

	private

end

class Pitch
	SHARP = "#"
	FLAT  = "b"

	PITCHES = [
		"A", ["A#", "Bb"], "B", "C", ["C#", "Db"],
		"D", ["D#", "Eb"], "E", "F", ["F#", "Gb"],
		"G", ["G#", "Ab"] ]

	def initialize(note: nil, index: 0)
		raise ArgumentError unless index >= 0 && index < PITCHES.size
		@note = process_note(note, index)
		find_pitch
	end

	def note(key_type = :sharp)
		if @pitch.is_a? Array
			case key_type
			when :sharp then @pitch.first
			when :flat  then @pitch.last
			else			 @pitch.first
			end
		else
			@pitch
		end
	end

	def natural?
		!(sharp? || flat?)
	end

	def sharp?
		@note =~ /#{SHARP}/	
	end

	def flat?
		@note =~ /#{FLAT}/
	end

	def next(interval)
		Pitch.new(index: (@pitch_index + interval.value) % PITCHES.size)
	end

	private

		def process_note(note, index)
			if note
				note.length == 1 ? note.upcase : note[0].upcase + note[1]
			else
				p = PITCHES[index]
				p.is_a?(Array) ? p[0] : p
			end
		end

		def find_pitch
			index = PITCHES.index do |pitch|
				if pitch.is_a?(Array)
					pitch.include?(@note)
				else
					pitch == @note
				end
			end

			raise ArgumentError unless index
			
			@pitch_index = index
			@pitch = PITCHES[index]
		end

end
