package org.api.mtgstock.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EntryValue<T,V> {

	private T key;
	private V value;
	
	@Override
	public String toString() {
		return key + ":" + value;
	}
}
